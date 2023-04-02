package scherbatyuk.shops.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import scherbatyuk.shops.domain.Bucket;

public interface BucketRepository extends JpaRepository<Bucket, Integer>{

}
