package ua.avekshin.web.optimization.ideatransfer.backend.data;

import org.springframework.data.repository.CrudRepository;
import ua.avekshin.web.optimization.ideatransfer.backend.model.Idea;

/**
 * Created with IntelliJ IDEA.
 * User: Oleksii
 * Date: 06.01.14
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public interface IdeaRepository extends CrudRepository<Idea, Long> {

}
