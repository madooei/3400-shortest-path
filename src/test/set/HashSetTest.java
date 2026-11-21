package set;

/** Runs the Set contract suite against HashSet. */
public class HashSetTest extends SetTest {

  @Override
  protected Set<Integer> createSet() {
    return new HashSet<>();
  }
}
