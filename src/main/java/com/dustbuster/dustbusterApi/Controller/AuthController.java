package com.dustbuster.dustbusterApi.Controller;

import com.dustbuster.dustbusterApi.Entity.Usuario;
import com.dustbuster.dustbusterApi.Jwt.JwtUtils;
import com.dustbuster.dustbusterApi.Model.Bean.AuthRequestBean;
import com.dustbuster.dustbusterApi.Model.Bean.JwtResponseBean;
import com.dustbuster.dustbusterApi.Model.Bean.MessageResponseBean;
import com.dustbuster.dustbusterApi.Repository.IUsuarioRepository;
import com.dustbuster.dustbusterApi.Service.impl.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RestTemplate restTemplate;

    @Autowired
    public AuthController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthRequestBean authRequestBean) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestBean.getCorreo(), authRequestBean.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Actualizar la fecha de última sesión
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreo(authRequestBean.getCorreo());
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            // Validar si el usuario está habilitado (enabled = 1)
            if (!usuario.getEnabled()) {
                return ResponseEntity.badRequest().body(new MessageResponseBean("Usuario no habilitado para iniciar sesión."));
            }

            usuario.setUltimaSesion(new Timestamp(System.currentTimeMillis()));
            usuarioRepository.save(usuario);

        } else {
            // Manejar el caso si el usuario no existe
            // Puedes lanzar una excepción, crear un nuevo usuario, etc.
            return ResponseEntity.badRequest().body(new MessageResponseBean("Usuario no encontrado."));
        }
        Usuario data = usuarioOptional.get();

        return ResponseEntity.ok(new JwtResponseBean(jwt, "Bearer", data));
    }

    private String extractOpenpayUserId(String responseBody) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("id").asText();
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponseBean> registerUser(@Valid @RequestBody Usuario usuario) throws Exception {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseBean("Error: Username is already taken!"));
        }
        String pass = encoder.encode(usuario.getPassword());
        usuario.setPassword(pass);
        usuario.setUltimaSesion(new Timestamp(System.currentTimeMillis()));
        usuario.setFechaRegistro(new Timestamp(System.currentTimeMillis()));

        // Consumir el API externo de Openpay
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth("sk_914de389c3a7405d914eb3a10ab7173e", "");

        String apiUrl = "https://sandbox-api.openpay.mx/v1/mqmsrg8kqp8emsh76dgj/customers";
        String requestBody = "{\"name\": \"" + usuario.getNombreCompleto() + "\", \"email\": \"" + usuario.getCorreo() + "\", \"phone_number\": \"" + usuario.getNumeroTelefono() + "\", \"requires_account\": false}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> openpayResponse = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        String openpayUserId = extractOpenpayUserId(openpayResponse.getBody());
        usuario.setUserIdOpenpay(openpayUserId);

        // Puedes hacer algo con la respuesta de Openpay si es necesario

        // Guardar el usuario en tu base de datos
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(new MessageResponseBean("User registered successfully!"));
    }



}
