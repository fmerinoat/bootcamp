package com.cost.bootcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cost.bootcamp.domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>{
	
	/*
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " \n#pageable\n",
			countQuery = "SELECT COUNT(ayu.id) FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
	)
	Page<Ayuda> getAyudasPersonasRequests(Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND ayu.id IN( :listIds )"
	)
	List<Ayuda> getAyudasPersonasByIds(@Param("listIds") List<Integer> listIds);

	@Query(nativeQuery = true, value = "SELECT ayud.* FROM Ayuda ayud"
			+ " WHERE ayud.ejercicio < :ejercicio"
			+ " AND ayud.interesado_id = :idInteresado"
			+ " AND ayud.denegada = 0"
			+ " ORDER BY ayud.ejercicio DESC"
			+ " LIMIT 1"
	)
	Ayuda getAnteriorAyuda(@Param("idInteresado") Long idInteresado, @Param("ejercicio") String ejercicio);

	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " LEFT JOIN actividades_entidades actividadEntidad ON actividadEntidad.id = ayu.actividad_entidad_id"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND (ayu.referencia=:referencia OR '0'=:referencia)"
			+ " AND (ayu.ejercicio LIKE CONCAT('%', :ejercicio, '%') OR '0'=:ejercicio)"
			+ " AND (ayu.apellidos LIKE CONCAT('%', :apellidos, '%') OR '0'=:apellidos)"
			+ " AND (ayu.provincia LIKE CONCAT('%', :provincia, '%') OR '0'=:provincia)"
			+ " AND (ayu.poblacion LIKE CONCAT('%', :poblacion, '%') OR '0'=:poblacion)"
			+ " AND (ayu.solicitant LIKE CONCAT('%', :solicitante, '%') OR '0'=:solicitante)"
			+ " AND ((ayu.importeconce =:importeConce AND '1'=:tipoImporte)"
			+ " OR (ayu.importeconce >:importeConce AND '2'=:tipoImporte)"
			+ " OR (ayu.importeconce <:importeConce AND '3'=:tipoImporte)"
			+ " OR '0'=:importeConce)"
			+ " AND (CAST(ayu.fechaconce AS DATE) =:concesionDateTime OR '0'=:concesionDate)"
			+ " \n#pageable\n",
			countQuery = "SELECT COUNT(ayu.id) FROM ayuda ayu"
			+ " LEFT JOIN actividades_entidades actividadEntidad ON actividadEntidad.id = ayu.actividad_entidad_id"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND (ayu.referencia=:referencia OR '0'=:referencia)"
			+ " AND (ayu.ejercicio LIKE CONCAT('%', :ejercicio, '%') OR '0'=:ejercicio)"
			+ " AND (ayu.apellidos LIKE CONCAT('%', :apellidos, '%') OR '0'=:apellidos)"
			+ " AND (ayu.provincia LIKE CONCAT('%', :provincia, '%') OR '0'=:provincia)"
			+ " AND (ayu.poblacion LIKE CONCAT('%', :poblacion, '%') OR '0'=:poblacion)"
			+ " AND (ayu.solicitant LIKE CONCAT('%', :solicitante, '%') OR '0'=:solicitante)"
			+ " AND ((ayu.importeconce =:importeConce AND '1'=:tipoImporte)"
			+ " OR (ayu.importeconce >:importeConce AND '2'=:tipoImporte)"
			+ " OR (ayu.importeconce <:importeConce AND '3'=:tipoImporte)"
			+ " OR '0'=:importeConce)"
			+ " AND (CAST(ayu.fechaconce AS DATE) =:concesionDateTime OR '0'=:concesionDate)"
	)
	Page<Ayuda> getFilteredAyudasPersonaRequest(@Param("referencia") Long referencia, 
			@Param("ejercicio") String ejercicio,
			@Param("apellidos") String apellidos,
			@Param("provincia") String provincia,
			@Param("poblacion") String poblacion,
			@Param("solicitante") String solicitante,
			@Param("tipoImporte") String tipoImporte,
			@Param("importeConce") Long importeConce,
			@Param("concesionDateTime") LocalDate concesionDateTime,
			@Param("concesionDate") String concesionDate,
			Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND (ayu.referencia=:referencia OR '0'=:referencia)"
			+ " AND (ayu.ejercicio =:ejercicio OR '0'=:ejercicio)"
			+ " AND (ayu.apellidos LIKE CONCAT('%', :apellidos, '%') OR '0'=:apellidos)"
			+ " AND (ayu.provincia LIKE CONCAT('%', :provincia, '%') OR '0'=:provincia)"
			+ " AND (ayu.poblacion LIKE CONCAT('%', :poblacion, '%') OR '0'=:poblacion)"
			+ " AND (ayu.solicitant LIKE CONCAT('%', :solicitante, '%') OR '0'=:solicitante)"
			+ " AND ((ayu.importeconce =:importeConce AND '1'=:tipoImporte)"
			+ " OR (ayu.importeconce >:importeConce AND '2'=:tipoImporte)"
			+ " OR (ayu.importeconce <:importeConce AND '3'=:tipoImporte)"
			+ " OR '0'=:importeConce)"
			+ " AND (CAST(ayu.fechaconce AS DATE) =:concesionDateTime OR '0'=:concesionDate)"
	)
	List<Ayuda> getFilteredAyudasPersonaRequest(@Param("referencia") Long referencia, 
			@Param("ejercicio") String ejercicio,
			@Param("apellidos") String apellidos,
			@Param("provincia") String provincia,
			@Param("poblacion") String poblacion,
			@Param("solicitante") String solicitante,
			@Param("tipoImporte") String tipoImporte,
			@Param("importeConce") Long importeConce,
			@Param("concesionDateTime") LocalDate concesionDateTime,
			@Param("concesionDate") String concesionDate);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND ayu.interesado_id = :idInteresado"
			+ " AND ayu.ejercicio = (SELECT max(ayu.ejercicio) FROM ayuda ayu WHERE ayu.interesado_id = :idInteresado)"
			)
	Ayuda findOneAyudaActualByInteresado(@Param("idInteresado") Long idInteresado);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND ayu.interesado_id =:idInteresado"
			+ " AND ayu.ejercicio =:ejercicio "
			)
	Ayuda findOneAyudaWhitEjercicioByInteresado(@Param("idInteresado") Long idInteresado, @Param("ejercicio") String ejercicio);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " INNER JOIN interesado inte"
			+ " ON inte.id = ayu.interesado_id"
			+ " INNER JOIN ayuda ayu2"
			+ " ON ayu2.interesado_id = inte.id"
			+ " AND ayu2.id = :idAyuda"
			)	
	List<Ayuda> findAyudasOfInteresadoByidAyuda(@Param("idAyuda") Long idAyuda);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " INNER JOIN interesado inte"
			+ " ON inte.id = ayu.interesado_id"
			+ " INNER JOIN ayuda ayu2"
			+ " ON ayu2.interesado_id = inte.id"
			+ " AND ayu2.id = :idAyuda"
			+ " ORDER BY ayu.ejercicio DESC"
			)	
	List<Ayuda> getAyudasHistorial(@Param("idAyuda") Long idAyuda);
	
	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND ayu.fechaconce BETWEEN :from AND :to"
			+ " AND ayu.denegada = 0"
			+ " ORDER BY ayu.referencia ASC"
			//+ " AND ayu.importeconce IS NOT NULL"
			//+ " AND ayu.importeconce > 0" 
	)
	List<Ayuda> getAyudasPersonasConcedidasByFechas(@Param("from") LocalDate from, @Param("to") LocalDate to);

	@Query(nativeQuery = true, value = "SELECT ayu.* FROM ayuda ayu"
			+ " WHERE ayu.tipo_interesado = 1"
			+ " AND (( ayu.fechaconce BETWEEN :from AND :to"
			+ " AND ayu.denegada = 0)"
			+ " OR ( ayu.denegada = 1"
			+ " AND ayu.ejercicio = YEAR(:from)))"
			+ " ORDER BY ayu.referencia ASC"
	)
	List<Ayuda> getAyudasPersonasByFechasAceptadasDenegadas(@Param("from") LocalDate from, @Param("to") LocalDate to);
		
	
	*/
}
