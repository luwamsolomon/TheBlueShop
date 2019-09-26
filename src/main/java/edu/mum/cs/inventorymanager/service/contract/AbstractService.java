package edu.mum.cs.inventorymanager.service.contract;

import edu.mum.cs.inventorymanager.model.entity.Merchant;

import java.util.List;

public interface AbstractService<T> {
    public T create(T t);
    public T findById(Long id);
    public List<T> findAll();
    public List<T> findAllByMerchant(Merchant merchant);
    public void delete(T t);
    public void deleteById(long id);
    public T update(T t);
}
