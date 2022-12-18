/**
 * This class represents an organ in a word-type list.
 *
 * @Rei Bashari , 315522623 . 
 * @05/06/2021
 */
public class WordNode
{
    private String _word;
    private int _sum;
    private WordNode _next;

    /**
     * Constructor for objects of class WordNode. 
     */
    public WordNode(String word)
    {
        _word = word;
        _next = null;
        _sum = 1;
    }

    /**
     * Second constructor for objects of class WordNode. 
     * @param word.
     * @param next.
     */
    public WordNode(String word, WordNode next)///////
    {
        _word = word;
        _next = next;
        _sum = 1;
    }

    /**
     * Third constructor for objects of class WordNode. 
     * @param word.
     * @param sum.
     */
    public WordNode(String word, int sum)
    {
        _word = word;
        _sum = sum;
        _next = null;
    }

    //methods 
    /** 
     * This method returns the word in this node.
     * @return The word.
     */
    public String getWord()
    {
        return _word;
    }

    /** 
     * This method returns the next object after this node.
     * @return The object _next.
     */
    public WordNode getNext()
    {
        return _next;
    }

    /** 
     * This method returns the frequency of the word.
     * @return The frequency of this word.
     */
    public int getSum()
    {
        return _sum;
    }

    /**
     * This method sets the word to another word. 
     * @param word - The new word.
     */
    public void setWord(String word)
    {
        _word = word;
    }

    /**
     * This method sets the feature _next of this word. 
     * @param node - The new next node.
     */
    public void setNext(WordNode node)
    {
        _next = node;
    }

    /**
     * This method sets the frequency of the word. 
     * @param sum - The new frequency of the word. 
     */
    public void setSum(int sum)
    {
        _sum = sum;
    }
    
    public String toString()
    {
        return "[\"" + _word + "\"," + _sum + "]";
    }
}
