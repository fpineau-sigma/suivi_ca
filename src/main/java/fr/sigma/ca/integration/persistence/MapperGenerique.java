package fr.sigma.ca.integration.persistence;

import java.util.Collection;
import java.util.List;

/**
 * Methodes generiques pour les mappers mapstruct
 *
 * @param <D> Type générique pour les DTO
 * @param <E> Type générique pour les Entités
 */
public interface MapperGenerique<D, E> {
  D toDto(E entity);

  E toEntity(D dto);

  List<D> toDto(List<E> entityList);

  List<E> toEntity(List<D> dtoList);

  Collection<D> toDto(Collection<E> entityCollection);

  Collection<E> toEntity(Collection<D> dtoCollection);
}
