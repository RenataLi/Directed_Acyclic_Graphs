package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * A graph class whose main task is
 * to check for cycles and throw an exception if such a cycle is formed.
 */
public class Graph {
    /**
     * V.elementAt(i) -- List of adjacencies with the i-th vertex.
     */
    private Vector<List<Integer>> listVector;
    /**
     * Lists of "gray" or visited vertices
     */
    private List<Integer> gray;
    /**
     * Lists of "black" ones of fully verified vertices
     */
    private List<Integer> black;
    /**
     * The current dive path is for debugging
     */
    private List<Integer> path;

    /**
     * Empty graph constructor.
     */
    private Graph() {
    }

    /**
     * @param edges      edges.ElementAt(i) -- edge of the graph.
     * @param n_vertices Number of vertices (vertices from 0 to n_vertices-1).
     */
    public Graph(Vector<int[]> edges, int n_vertices) {
        listVector = new Vector<List<Integer>>();
        for (int i = 0; i < n_vertices; i++) listVector.add(new LinkedList<Integer>());
        for (int i = 0; i < edges.size(); i++) {
            int from = edges.elementAt(i)[0];
            int to = edges.elementAt(i)[1];
            listVector.elementAt(from).add(to);
        }
        gray = new LinkedList<Integer>();
        black = new LinkedList<Integer>();
        path = new LinkedList<Integer>();
    }

    /**
     * Getting the graph path for clearer representation and debugging.
     *
     * @return
     */
    public String getPath() {
        String str = "";
        for (int k : path) {
            str += k + "->";
        }
        return str;
    }

    /**
     * Implementation of the DFS algorithm for finding cycles.
     *
     * @return False if cucles were not found and true if cycles were found.
     */
    public boolean dFS() {
        gray = new LinkedList<Integer>();
        black = new LinkedList<Integer>();

        for (int v = 0; v < listVector.size(); v++) {
            // We descend to the next non-black vertex.
            if (!gray.contains(v) && !black.contains(v))
                if (dFSDive(v)) return true; // The cycle was found.
            assert (path.isEmpty()); // Path should be empty here.
        }
        // Сycles were not found.
        return false;
    }

    /**
     * Traversing in depth and marking nodes in black or gray.
     *
     * @param element
     * @return
     */
    private boolean dFSDive(int element) {
        gray.add(element); // Let's mark it as gray - we went through it further.
        path.add(element);
        ListIterator<Integer> it = listVector.elementAt(element).listIterator();
        while (it.hasNext()) {
            int next_v = it.next();
            if (gray.contains(next_v)) { // If as a result of the current dive visited.
                path.add(next_v);
                return true;
            } else {
                if (!black.contains(next_v)) { // If you haven't checked before.
                    if (dFSDive(next_v))
                        return true;
                }
            }
        }
        // The current dive is successful - there is nowhere else to dive.
        // We remove the "gray" labels.
        gray.remove(gray.indexOf(element));
        // We put "black" - no cycles go through these elements.
        // And they don't need to be checked anymore.
        black.add(element);
        path.remove(path.size() - 1);
        return false;
    }
}

