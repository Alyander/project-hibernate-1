package com.game.repository;

import com.game.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.stereotype.Repository;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Repository(value = "db")
public class PlayerRepositoryDB implements IPlayerRepository {
    private final SessionFactory sessionFactory;
    public PlayerRepositoryDB() {
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        settings.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/rpg");
        settings.put(Environment.USER, "anatolii");
        settings.put(Environment.PASS, "6879");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        sessionFactory = new Configuration()
                .addProperties(settings)
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
    }

    @Override
    public List<Player> getAll(int pageNumber, int pageSize) {
        int temp = pageNumber * pageSize;
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("", Player.class).setFirstResult(temp).setMaxResults(pageSize).list();
        }
    }


    @Override
    public int getAllCount() {
        try (Session session = sessionFactory.openSession()) {
            Long count = session.createQuery("select count(p) from Player p", Long.class)
                    .uniqueResult();
            return Math.toIntExact(count);
        }
    }


    @Override
    public Player save(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(player);
            session.getTransaction().commit();
            return player;
        }
    }

    @Override
    public Player update(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(player);
            session.getTransaction().commit();
            return player;
        }
    }

    @Override
    public Optional<Player> findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Player.class, id));
        }
    }

    @Override
    public void delete(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(player);
            session.getTransaction().commit();
        }
    }

    @PreDestroy
    public void beforeStop() {
        sessionFactory.close();
    }
}