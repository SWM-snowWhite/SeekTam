package food.backend.search.model;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface FoodRepository extends JpaRepository<ViewsRanking, Long> {
    static void increaseViewCount() {
    }

    @Override
    default List<ViewsRanking> findAll() {
        return null;
    }

    @Override
    default List<ViewsRanking> findAll(Sort sort) {
        return null;
    }

    @Override
    default Page<ViewsRanking> findAll(Pageable pageable) {
        return null;
    }

    @Override
    default List<ViewsRanking> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(Long aLong) {

    }

    @Override
    default void delete(ViewsRanking entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    default void deleteAll(Iterable<? extends ViewsRanking> entities) {

    }

    @Override
    default void deleteAll() {

    }

    @Override
    default <S extends ViewsRanking> S save(S entity) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<ViewsRanking> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Long aLong) {
        return false;
    }

    @Override
    default void flush() {

    }

    @Override
    default <S extends ViewsRanking> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    default void deleteAllInBatch(Iterable<ViewsRanking> entities) {

    }

    @Override
    default void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    default void deleteAllInBatch() {

    }

    @Override
    default ViewsRanking getOne(Long aLong) {
        return null;
    }

    @Override
    default ViewsRanking getById(Long aLong) {
        return null;
    }

    @Override
    default ViewsRanking getReferenceById(Long aLong) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    default <S extends ViewsRanking> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    default <S extends ViewsRanking> long count(Example<S> example) {
        return 0;
    }

    @Override
    default <S extends ViewsRanking> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    default <S extends ViewsRanking, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
