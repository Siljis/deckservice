package siljis.util.deckservice.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 * Deck repository. Simple in-memory storage with support for CRUD operations.
 * To be replaced with a durable storage.
 *
 * @author Silja Tirronen
 */
@Singleton
public class DeckRepository {

    private Map<String, String> lookup;

    @PostConstruct
    public void init() {
        lookup = new ConcurrentHashMap<>();
    }

    public List<String> readNames() {
        return new ArrayList<>(lookup.keySet());
    }

    public boolean create(String key, String value) {
        return lookup.putIfAbsent(key, value) == null;
    }

    public String read(String name) {
        return lookup.get(name);
    }

    public boolean update(String key, String value) {
        return lookup.replace(key, value) != null;
    }

    public boolean delete(String key) {
        return lookup.remove(key) != null;
    }

}
