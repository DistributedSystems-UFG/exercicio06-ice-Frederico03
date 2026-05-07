module Demo
{
    sequence<string> StringSeq;

    interface Printer
    {
        // Original method: print a single string and return a confirmation
        string printString(string s);

        // New method: print multiple lines and return the number of lines printed
        int printLines(StringSeq lines);

        // New method: return how many print operations have been performed
        int countPrints();

        // New method: clear the print history/counter
        void clear();
    }
}
