package users.comments.service.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCommentsRepository extends JpaRepository<UsersComments, Integer>
{

}

