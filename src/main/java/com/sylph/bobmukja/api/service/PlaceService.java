package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place get(long id) {
        return placeRepository.findById(id).get();
    }

    public List<Place> getList() {
        return placeRepository.findAll();
    }
}
