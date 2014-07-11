package com.tynja.stubit;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author Tommy Tynj&auml;
 */
public class JpaEntityWithCollectionsInterfaces {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Collection<String> collection;

    @Column(nullable = false)
    private List<String> list;

    @Column(nullable = false)
    private Set<Integer> set;

    @Column(nullable = false)
    private SortedSet<Long> sortedSet;

    @Column(nullable = false)
    private BlockingDeque blockingDeque;

    @Column(nullable = false)
    private BlockingQueue blockingQueue;

    @Column(nullable = false)
    private Deque deque;

    @Column(nullable = false)
    private Queue queue;

    @Column(nullable = false)
    private NavigableSet navigableSet;

    @Column(nullable = false)
    private TransferQueue transferQueue;

    @Column(nullable = false)
    private Iterable iterable;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Collection<String> getCollection() {
        return collection;
    }

    public void setCollection(final Collection<String> collection) {
        this.collection = collection;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(final List<String> list) {
        this.list = list;
    }

    public Set<Integer> getSet() {
        return set;
    }

    public void setSet(final Set<Integer> set) {
        this.set = set;
    }

    public SortedSet<Long> getSortedSet() {
        return sortedSet;
    }

    public void setSortedSet(final SortedSet<Long> sortedSet) {
        this.sortedSet = sortedSet;
    }

    public BlockingDeque getBlockingDeque() {
        return blockingDeque;
    }

    public void setBlockingDeque(final BlockingDeque blockingDeque) {
        this.blockingDeque = blockingDeque;
    }

    public BlockingQueue getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(final BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public Deque getDeque() {
        return deque;
    }

    public void setDeque(final Deque deque) {
        this.deque = deque;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(final Queue queue) {
        this.queue = queue;
    }

    public NavigableSet getNavigableSet() {
        return navigableSet;
    }

    public void setNavigableSet(final NavigableSet navigableSet) {
        this.navigableSet = navigableSet;
    }

    public TransferQueue getTransferQueue() {
        return transferQueue;
    }

    public void setTransferQueue(final TransferQueue transferQueue) {
        this.transferQueue = transferQueue;
    }

    public Iterable getIterable() {
        return iterable;
    }

    public void setIterable(final Iterable iterable) {
        this.iterable = iterable;
    }
}
