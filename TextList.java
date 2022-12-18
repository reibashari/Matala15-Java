/**
 * This class represents the word group in the form of a list sorted in lexical order.

 *
 * @Rei Bashari , 315522623 . 
 * @05/06/2021
 */
public class TextList
{
    private WordNode _head;

    /**
     * Constructor for objects of class TextList. 
     */
    public TextList()
    {
        _head = null;
    }

    /**
     * Second constructor for objects of class TextList. 
     * @param text.
     */
    public TextList(String text)
    {
        if (text.length() == 0)
        {
            _head = null;
        }
        else
        {
            while (text.length() > 0)
            {
                int spaceIndex = text.indexOf(" ");
                if (spaceIndex < 0)
                {
                    addDataWithoutSorting(text);
                    text = "";
                }
                else
                {
                    String word = text.substring(0, spaceIndex);
                    addDataWithoutSorting(word);
                    text = text.substring(spaceIndex + 1);
                }
            }

            sortList();
        }
    }

    //methods 
    /** 
     * This method gets a word and adds it to the right place on the list
     * @param word.
     */
    public void addToData(String word)
    {
        if (word.length() == 0)
        {
            return;
        }

        if (_head == null)
        {
            _head = new WordNode(word);
            return;
        }
        if (word.compareTo(_head.getWord()) < 0)
        {
            _head = new WordNode(word, _head);
            return;
        }
        if (word.compareTo(_head.getWord()) == 0)
        {
            _head.setSum(_head.getSum()+1);
            return;
        }
        WordNode curr = _head;
        WordNode prev = null;
        while (curr.getNext() != null)
        {
            if (word.compareTo(curr.getWord()) < 0)
            {
                WordNode newNode = new WordNode(word);
                newNode.setNext(curr);
                prev.setNext(newNode);
                return;
            }
            if (word.compareTo(curr.getWord()) == 0)
            {
                curr.setSum(curr.getSum() + 1);
                return;
            }

            prev = curr;
            curr = curr.getNext();
        }

        if (word.compareTo(curr.getWord()) < 0)
        {
            WordNode newNode = new WordNode(word);
            newNode.setNext(curr);
            prev.setNext(newNode);
            return;
        }
        if (word.compareTo(curr.getWord()) == 0)
        {
            curr.setSum(curr.getSum() + 1);
            return;
        }

        curr.setNext(new WordNode(word));
    }

    /** 
     * This method returns the total number of words in the text.
     */
    public int howManyWords()
    {
        WordNode curr = _head;
        int count = 0;
        while (curr != null)
        {
            count += curr.getSum();
            curr = curr.getNext();
        }

        return count;
    }

    /** 
     * This method returns the number of different words in the text
     */
    public int howManyDifferentWords()
    {
        WordNode curr = _head;
        int count = 0;
        while (curr != null)
        {
            count++;
            curr = curr.getNext();
        }

        return count;
    }

    /** 
     * This method returns the most common word in the text.
     */
    public String mostFrequentWord()
    {
        if (_head == null)
        {
            return "";
        }

        WordNode mostFreq = _head;
        WordNode curr = _head.getNext();
        while (curr != null)
        {
            if (curr.getSum() > mostFreq.getSum())
            {
                mostFreq = curr;
            }

            curr = curr.getNext();
        }

        return mostFreq.getWord();
    }

    /** 
     * This method gets a letter, and returns the number of words in the text that begin with that letter.
     * param letter.
     */
    public int howManyStarting(char letter)
    {
        WordNode curr = _head;
        int count = 0;
        while (curr != null && curr.getWord().charAt(0) <= letter)
        {
            if (curr.getWord().charAt(0) == letter)
            {
                count += curr.getSum();
            }

            curr = curr.getNext();
        }

        return count;
    }

    /** 
     * This method returns the letter that most words begin with in the text.
     */
    public char mostFrequentStartingLetter()
    {
        return mostFrequentStartingLetter(_head, ' ', 0);
    }

    /** 
     * This method passes the list to a string.
     */
    public String toString()
    {
        String str = "";
        for (WordNode curr = _head; curr != null; curr = curr.getNext())
        {
            if (str.length() > 0)
            {
                str += "\n";
            }
            str += curr.getWord() + "\t" + curr.getSum();
        }

        return str;
    }

    //private method to calculate the most common first letter.
    private char mostFrequentStartingLetter(WordNode curr, char mostFrequentStartingLetter,
    int maxCount)
    {
        if (curr == null)
        {
            return mostFrequentStartingLetter;
        }

        int currWordStartingCount = howManyStarting(curr.getWord().charAt(0));
        if (currWordStartingCount > maxCount)
        {
            maxCount = currWordStartingCount;
            mostFrequentStartingLetter = curr.getWord().charAt(0);
        }

        return mostFrequentStartingLetter(curr.getNext(), mostFrequentStartingLetter, maxCount);
    }

    //private method is calculate the middle node according to give a particular list.
    private void sortList()
    {
        WordNode last = tail();
        _head = mergeSortList(_head, last);
    }
    
    //private method to find the tail in the list.
    private WordNode tail()
    {
        if (_head == null)
        {
            return null;
        }

        WordNode curr = _head;
        while (curr.getNext() != null)
        {
            curr = curr.getNext();
        }

        return curr;
    }

    //private method that sorts the list in a lexical order.
    private WordNode mergeSortList(WordNode start, WordNode end)
    {
        if (start == end)
        {
            return new WordNode(start.getWord());
        }

        WordNode midWord = findMid(start, end);
        WordNode node1 = mergeSortList(start, midWord);
        WordNode node2 = mergeSortList(midWord.getNext(), end);
        WordNode listHead = null;
        WordNode listTail = null;
        while (node1 != null && node2 != null)
        {
            WordNode node = null;
            if (node1.getWord().compareTo(node2.getWord()) < 0)
            {
                node = node1;
                node1 = node1.getNext();
            }
            else
            {
                node = node2;
                node2 = node2.getNext();
            }
            if (listHead == null)
            {
                listHead = listTail = new WordNode(node.getWord(), node.getSum());
            }
            else
            {
                if (node.getWord().equals(listTail.getWord()))
                {
                    listTail.setSum(listTail.getSum() + node.getSum());
                }
                else
                {
                    listTail.setNext(new WordNode(node.getWord(), node.getSum()));
                    listTail = listTail.getNext();
                }
            }
        }

        while (node1 != null)
        {
            if (node1.getWord().equals(listTail.getWord()))
            {
                listTail.setSum(listTail.getSum() + node1.getSum());
            }
            else
            {
                listTail.setNext(new WordNode(node1.getWord(), node1.getSum()));
                listTail = listTail.getNext();
            }

            node1 = node1.getNext();
        }

        while (node2 != null)
        {
            if (node2.getWord().equals(listTail.getWord()))
            {
                listTail.setSum(listTail.getSum() + node2.getSum());
            }
            else
            {
                listTail.setNext(new WordNode(node2.getWord(), node2.getSum()));
                listTail = listTail.getNext();
            }

            node2 = node2.getNext();
        }

        return listHead;
    }

    //private method to find the mid node in the list.
    private WordNode findMid(WordNode start, WordNode end)
    {
        WordNode mid = start;
        while (start != end && start.getNext() != end)
        {
            start = start.getNext().getNext();
            mid = mid.getNext();
        }

        return mid;
    }

    //private method to add a new node without sorting.
    private void addDataWithoutSorting(String word)
    {
        WordNode newWord = new WordNode(word, _head);
        _head = newWord;
    }
}
