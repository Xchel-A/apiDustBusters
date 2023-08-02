package com.dustbuster.dustbusterApi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@Table(name = "Servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer serviceId;

    @ManyToOne
    @JoinColumn(name = "user_id_cliente", referencedColumnName = "user_id")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name = "user_id_limpiador", referencedColumnName = "user_id")
    private Usuario limpiador;

    @Column(name = "descripcion_servicio", columnDefinition = "TEXT")
    private String descripcionServicio;

    @Column(name = "tamano_inmueble", length = 50)
    private String tamanoInmueble;

    @Column(name = "plantas", length = 10)
    private String plantas;

    @Column(name = "oferta_cliente", precision = 10, scale = 2)
    private BigDecimal ofertaCliente;

    @Column(name = "oferta_aceptada")
    private Boolean ofertaAceptada;

    @Column(name = "fecha_servicio")
    private Date fechaServicio;

    @Column(name = "hora_inicio")
    private Time horaInicio;

    @Column(name = "hora_fin")
    private Time horaFin;

    private String latitud;
    private String longitud;

    /**
     * , 'pendiente' = 0, 'en_progreso'=1, 'completado'=2)"
     */
    @Column(name = "estado")
    private Integer estado;

    public Servicio() {

    }
}