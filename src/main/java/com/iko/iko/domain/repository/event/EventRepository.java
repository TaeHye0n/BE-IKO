package com.iko.iko.domain.repository.event;

import  com.iko.iko.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer>,EventRepositoryCustom {
}
