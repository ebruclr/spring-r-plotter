package pro1;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pro1.DataDocument;

@Repository
public interface DataRepository extends MongoRepository<DataDocument, String> {
}