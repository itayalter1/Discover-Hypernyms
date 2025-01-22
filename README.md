README:
markdown
Copy
Edit
# 📚 Discover Hypernyms  

**Discover Hypernyms** is a Java-based command-line tool for identifying hypernyms (broad categories) for a given word (lemma) within a corpus of text files. It uses regular expressions to analyze noun phrases in text and outputs the results sorted by frequency.  

---

## ✨ Features  
- Extracts hypernyms for a given lemma using advanced regex patterns.  
- Supports multiple text files in a directory.  
- Displays results sorted by occurrence count.  

---

## 🚀 Getting Started  

### Prerequisites  
- Java 8 or higher.  

### Cloning the Repository  
1. Open your terminal.  
2. Run the following command to clone the repository:  
   ```bash
   git clone <repository-url>
   cd <repository-folder>
🏗️ How to Run
Compile the Program
Navigate to the project folder:
bash
Copy
Edit
cd <repository-folder>
Compile the program:
bash
Copy
Edit
javac *.java
Execute the Program
Run the program with the directory path and lemma as arguments:

bash
Copy
Edit
java DiscoverHypernym <directory-path> <lemma>
<directory-path>: Path to the directory containing text files.
<lemma>: The word for which you want to discover hypernyms.
📂 Example Usage
Input
Command:

bash
Copy
Edit
java DiscoverHypernym /path/to/files program
Directory /path/to/files contains a file with this content:

php
Copy
Edit
<np>Programs</np> which is <np>an arrangement</np>
Output
plaintext
Copy
Edit
an arrangement: (1)
🧪 Example Code Execution
java
Copy
Edit
HashMap<String, Integer> map = new HashMap<>();
DiscoverHypernym.processLine("<np>Programs</np> which is <np>an arrangement</np>", "program", map);
System.out.println(map);
Output:
plaintext
Copy
Edit
{an arrangement=1}
📜 Patterns Used
Pattern1: Matches "NP such as NP".
Pattern2: Matches "such NP as NP".
Pattern3: Matches "NP including NP".
Pattern4: Matches "NP especially NP".
Pattern5: Matches "NP which is (an example/a kind/a class) of NP".
🛠️ Project Structure
DiscoverHypernym.java: Main class for processing text and identifying hypernyms.
BasePattern.java: Parent class implementing common logic for pattern matching.
Pattern Classes:
Pattern1.java, Pattern2.java, Pattern3.java, Pattern4.java, Pattern5.java
Patterns.java: Interface for defining pattern logic.
