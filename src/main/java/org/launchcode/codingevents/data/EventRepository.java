package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chris Bay
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    //    extending the Crud repository with the event model and the Primary ID type(Integer)
//    Repository<T,ID> Remember CrudRepository is a built in Java class.
}
