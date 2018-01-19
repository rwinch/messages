package sample;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Rob Winch
 */
public interface MessageRepository extends CrudRepository<Message,Long> {
}
