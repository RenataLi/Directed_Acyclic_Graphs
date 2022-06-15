package org.example;

/**
 * Class of Exception that throws when there are circles in graph.
 */
public class DAGConstraintException extends Exception {
    DAGConstraintException(String msg) {
        super(msg);
    }
}
