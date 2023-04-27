package com.example.gitsearcher.mapper;

import java.util.List;

public interface Mapper<T, E> {
    T toDto(E entity);

    default List<T> toDto(List<E> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
}
