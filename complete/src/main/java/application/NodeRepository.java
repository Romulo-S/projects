package application;

import pojo.Node;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository  extends CrudRepository<Node,Long> {
}
