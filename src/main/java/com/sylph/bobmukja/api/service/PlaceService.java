package com.sylph.bobmukja.api.service;

import com.sylph.bobmukja.api.domain.entity.Place;
import com.sylph.bobmukja.api.domain.repository.PlaceRepository;
import com.sylph.bobmukja.api.web.dto.PlaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place get(long id) {
        return placeRepository.findById(id).orElse(null);
    }

    public List<Place> getList() {
        return placeRepository.findAll();
    }

    public boolean save(List<PlaceRequest> placeRequestList) {
        placeRequestList.stream().map(request -> placeRepository.save(request.toEntity()));
        return true;
    }

    public boolean delete(List<Long> placeIdList) {
        List<Place> placeList = placeIdList.stream().map(p -> placeRepository.findByIdAndDeletedIsFalse(p).orElse(null)).toList();
        placeList.stream().map(Place::deleted);
        return true;
    }

}
