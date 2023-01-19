package com.lcb.repository;

import com.lcb.domain.Books;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Repository
public class BookRepositoryImpl implements BookRepository{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Books books) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(books);

        tx.commit();
        session.close();

    }

    @Override
    public void loan(Books books) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(books);

        tx.commit();
        session.close();

    }

    @Override
    public List<Books> getAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<Books> courseList = session.createQuery("FROM Books ", Books.class).getResultList();
        tx.commit();
        session.close();

        return courseList;
    }

    @Override
    public Optional<Books> findById(Long id) {

        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        Books books=session.get(Books.class,id);
        Optional<Books> optionalBooks=Optional.ofNullable(books);//null yerine bos bir optinal

        tx.commit();
        session.close();
        return optionalBooks;
    }

    @Override
    public Optional<List<Books>> findByPersonName(String borrowername) {
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();

        Optional<List<Books>> optionalBorrowerName=Optional.ofNullable(Collections.singletonList(session.get(Books.class, borrowername)));//null yerine bos bir optinal

        tx.commit();
        session.close();
        return optionalBorrowerName;
    }

    @Override
    public void delete(Long id) {
        Session session=sessionFactory.openSession();
        Transaction tx=session.beginTransaction();
        Books books=session.load(Books.class,id);
        session.delete(books);

        tx.commit();
        session.close();
    }
}
