/**
 * This code has been taken from:
 * https://github.com/rayzhangcl/ESDemo
 * 
 * @author Chenlei Zhang - Original Owner
 */

package c301.AdventureBook.ElasticSearch;

import java.util.Collection;


public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits;
    }
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}