package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;

import java.util.Optional;

public class QuoteDao implements CrudDao<Quote, String> {

    private Connection c;

    @Override
    public Quote save(Quote entity) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Optional<Quote> findById(String s) throws IllegalArgumentException {
        return Optional.empty();
    }

    @Override
    public Iterable<Quote> findAll() {
        return null;
    }

    @Override
    public void deleteById(String s) throws IllegalArgumentException {

    }

    @Override
    public void deleteAll() {

    }
}
