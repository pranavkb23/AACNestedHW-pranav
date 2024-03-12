package structures;

/**
 * @author Samuel Rebelsky
 * @author Pranav K Bhandari
 * An easy way to store key/value pairs.  We assume that other
 * classes will access fields directly.
 */
public class KVPair<K,V> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The key.
   */
  K key;

  /**
   * The value.
   */
  V value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an empty key/value pair.
   */
  KVPair() {
    this(null, null);
  } // KVPair()

  /**
   * Create a new key/value pair.
   */
  KVPair(K key, V value) {
    this.key = key;
    this.value = value;
  } // KVPair(K,V)

  // +------------------+--------------------------------------------
  // | Standard methods |
  // +------------------+

  public KVPair<K,V> clone() {
    return new KVPair<K,V>(this.key, this.value);
  } // clone()

  /**
   * Set the value associated with a given key. 
   */
  public void set(K key, V value){
    this.key = key;
    this.value = value;
  }
  /**
   * Get the key of the KVPair. 
   */
  public K getKey(){
    return this.key;
  }

  /**
   * Get the value of the KVPair. 
   */
  public V getValue(){
    return this.value;
  }

  public String toString() {
    return "{ " + this.key.toString() + " : " + this.value.toString() + " }";
  } // toString()
} // class KVPair

