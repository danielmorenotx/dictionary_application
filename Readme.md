Building a dictionary application - Java
==========================================

## Part 1. Overview

The final project is to build a console-based dictionary application that allows users to perform various operations on a dictionary of words and their definitions. The application will utilize the concepts learned in Java, such as data types, variables, control flow, loops, methods, scopes, arrays, ArrayLists, Object-Oriented Programming (OOP), inheritance, polymorphism, abstract classes, interfaces, exception handling, file I/O, and the Java Stream API.

The dictionary application will be a command-line interface (CLI) program that runs in the console without a graphical user interface (GUI). Users will interact with the application by typing commands and receiving text-based output.

The application will have the following main features:

1. Load a dictionary from a text file containing word information.
2. Allow users to search for words and display their definitions.
3. Enable users to add new words and definitions to the dictionary.
4. Provide the ability to remove words and their definitions from the dictionary.
5. Save the updated dictionary back to the text file.


## Part 2. Project Structure

For this project, you will be provided with a starter code that you need to clone in the same directory this readme.md file is in. Do not fork the repository; instead, clone it to your local machine.

The starter code has the following package structure:

1. src: This is the main source code directory.
- **DictionaryApp.java:** This is the entry point of the application, where the main method resides.
- **interfaces package:** This package contains any necessary interfaces that you might need to implement for the project. You will have to use inter
- **domain package:** This package is intended for the core classes of the application, such as the  Word, WordReader, WordWriter, and other classes. 
  
Additionally, you may need to create the following packages:

**utils package:** This package can contain utility classes, such as validation utilities (string length etc...), string manipulation utilities, or any other helper classes you might need.

**exceptions package:** As a bonus, If you plan to create custom exception classes for the application, you can place them in this package. 

### If you face an error opening and running the starter code follow this.

1. If you see this error 
![error](./assets/1.png)
- Click on the setup SDK on the far right of the error and select a verison (21)

2. If its another error
- create a new project and inside the src folder create the same package names and the DictionaryApp class.

### what is the .keep and .gitignore

- .keep is used to push empty folders to git, like the packages in your app. They jave .keep files so that git accepts the empty packages. You can remove them after cloning the repository,
- .gitignore helps you to tell git to not include a certain file or folder in a commit, so that they will not be pushed. For example, this .gitignore has .idea inside of it as we dont need to share .idea folders.

## Part 3. The application 

In this part, you will focus on creating the command-line interface (CLI) prompts for the dictionary application. When the user runs the app (the main method inside DictionaryApp.java), they should see the following options:

```
****************************************************
Welcome

1. Find a word(s)
2. Find words by definition
3. Find all words that start with -
4. Find all words that end with -
5. Find all words containing -
6. Add a word
7. Delete a word
8. History
9. Creator
10. Exit
****************************************************
```
Here's an explanation of what each option does:

1. **Find a word(s):** This option allows the user to search for a specific word in the dictionary. The application will prompt the user to enter a word, and it will display the definition- part of speach and example usage of that word if found. **Remeber!** there could be duplicate words so if there are multiple words, they should all show up. 

2. **Find words by definition:** With this option, the user can search for words based on their definitions. The application will prompt the user to enter a definition **or a part of a definition**, and it will display all the words in the dictionary that match that definition.
   - For example, if a user selects Find words by definition and used "greeting" as a search, it should show all words with definition containing "greeting" like hi, and hello.

3. **Find all words that start with -:** This option enables the user to search for words that start with a specific prefix. The application will prompt the user to enter a prefix, and it will display all the words in the dictionary that begin with that prefix.

4. **Find all words that end with -:** Similar to the previous option, this allows the user to search for words that end with a specific suffix. The application will prompt the user to enter a suffix, and it will display all the words in the dictionary that end with that suffix.

5. **Find all words containing -:** This option allows the user to search for words that contain a specific substring. The application will prompt the user to enter a substring, and it will display all the words in the dictionary that include that substring.

6. **Add a word:** This option enables the user to add a new word and its definition to the dictionary. The application will prompt the user to enter the word, its definition, parts of speech (verb/noun etc . . .) and usage example. A user **must** enter the word and its definition and can leave parts of speech and usage example empty. If a user tries to procees without entering the word or its definition, the application should not allow it (either throw exception displaying the reason and exit or ask the user to enter again). Remember this should write the word to file.
   
7. **Delete a word:** With this option, the user can remove a word and its associated definition, parts of speech (verb/noun etc . . .) and usage example from the dictionary. The application will prompt the user to enter the word they want to delete, and it will remove that word from the dictionary if found. This should delete all the words in the dictionary, Remember this should deelte the words from file as well.

8. **History:** This option displays a history of the user's recent searches (option 1. Find a word(s)) within the dictionary application. The is expected to clear up when the app closes so a new history will be there on each session. In other words, for the basic requirement, no need to persist this in file.

9. **Creator:** This option provides information about the creator(s) of the dictionary application, such as their names, contact information, or any other relevant details.
    
10. **Exit:** This option allows the user to exit the dictionary application.

By presenting these options to the user, the dictionary application provides a user-friendly interface for performing various operations on the dictionary, such as searching, adding, and deleting words, as well as enabling advanced searches using prefixes, suffixes, and substrings.

To implement the search options, you will need to utilize the Java Stream API and its various methods, such as filter, map, and collect (toList()). These methods will allow you to efficiently search and manipulate the dictionary data based on the user's input.


## Part 4 - File Format

In this section, we will explain the file format used to store the dictionary data, including words, definitions, parts of speech, and example usages.

The dictionary data will be stored in a plain text file (.txt) and file named `dictionary.txt` inside the `lib` folder. 

It will havethe following format:

```
word | definition | part of speech | example usage
```

Each line in the file represents a single word entry, with the different components separated by the pipe (`|`) character. The components are arranged in the following order:

**Word:** The first component is the word itself.

**Definition:** The second component is the definition of the word.

**Part of Speech:** The third component is the part of speech of the word, such as noun, verb, adjective, adverb, etc.

**Example Usage:** The fourth component is an example sentence demonstrating the usage of the word.

Here's an example of how the entries should be formatted in the text file:

```
programming | The process of writing computer programs | noun | Programming is a crucial skill for software developers.

analyze | To examine something methodically and in detail | verb | The scientist analyzed the data to draw conclusions.

resilient | Able to withstand or recover quickly from difficult conditions | adjective | The city's infrastructure proved to be resilient during the earthquake.
```

By following this file format, the dictionary application can easily read and parse the data from the text file, allowing for efficient storage and retrieval of word information.
When using a FileReader (or any other file reading mechanism if you are willing to be adventurous) to read the dictionary data from the text file, the presence of a new line character (`\n`) indicates the start of a new word entry. The pipe `|` can effectively be used as a delimiter to separate the different components of each word entry in the text file to differentiate the class attributes such as word, definition, part of speech, and example.

## Part 5. Additional Application Requirements

In addition to the core functionality of the dictionary application, there are some additional requirements and considerations to enhance its performance and usability:

1. **Loading Dictionary Data into Memory:** When the application starts, it should read all the words, definitions, parts of speech, and example usages from the dictionary.txt file and load them into an ArrayList (in memory). This approach will ensure that word searches and other operations are performed on the in-memory data, rather than reading from the file each time, resulting in faster performance.
2. **Updating In-Memory Data and File:** Whenever a user adds or deletes a word from the dictionary, the application should update both the in-memory data structure (e.g., ArrayList) and the dictionary.txt file on disk. This way, the changes made during the current session are persisted and reflected in the file, ensuring that the updated dictionary is available the next time the application is launched.

## Part 6. Project recommendation 

To effectively organize and structure the dictionary application project, we recommend the following class and interface structure, this is just a recommendation and not a strict requirement:

1. **Word class:**
This class should represent a word entry in the dictionary.
It should encapsulate the word, definition, part of speech, and example usage.
Provide appropriate constructors, getters, and setters for these attributes.

2. **WordWriter interface:**
Define an interface named WordWriter with all the required methods, e.g., void writeWord(Word word).
This interface will be implemented by any class responsible for writing word entries to a file or any other data source.

3. **WordReader interface:**
Define an interface named WordReader with all the required methods, e.g., Word readWords().
This interface will be implemented by any class responsible for reading word entries from a file or any other data source.

4. **WordWriter class:**
Implement the WordWriter interface.
This class should handle writing word entries to a text file.

5. **WordReader class:**
Implement the WordReader interface.
This class should handle reading word entries from a text file.

6. **DictionaryManager class:**
This class should be responsible for managing the dictionary operations.
It should have methods for loading the dictionary from a data source (e.g., file), saving the dictionary, and performing operations like searching, adding, and removing words.
Utilize the WordReader and WordWriter classes to read and write word entries to and from the data source.

7. **Menu class:**
This class should handle the command-line interface (CLI) and user interactions.
Display the menu options (1-9) and prompt the user for input.
Based on the user's input, call the appropriate methods in the DictionaryManager class to perform the desired operation.

8. **Utilities class:**
Create a class named Utilities (or any other suitable name) for utility methods.
This class should contain static methods for string validation, parsing, and other common operations.
Example methods: isValidWord(String word), parseDefinition(String definition), formatWordEntry(Word word), etc.

## Part 7. Project Tip: Start Small and Connect the Dots

When working on a complex project like the dictionary application, it's important to break it down into smaller, manageable tasks and gradually build upon them. Here's a project tip that can help you approach the development process effectively:

Start Small and Connect the Dots:

1. **Begin with the core classes:**
- Focus on creating the Word class first, as it represents the fundamental unit of the dictionary application.
- Implement the necessary attributes (word, definition, part of speech, example usage) and their respective getters and setters.
- Ensure that the Word class is working correctly before moving on to other components.
- Create a new word instance and see that it works as expected.

1. **Implement the WordReader and WordWriter classes:**

- Create the WordReader and WordWriter interfaces, defining the contract for reading and writing word entries.
 - Implement the WordReader and WordWriter classes, which will handle file-based operations for reading and writing word entries, respectively.
- Ensure they can correctly read and write word entries to and from a file.
- Create an instance of WordReader and WordWriter to see you can read and write to dictionary.txt file in the requested format.

3. **Develop the DictionaryManager class:**
- Create the DictionaryManager class, which will act as the central component for managing the dictionary operations.
- Implement methods for loading the dictionary from a file (using the WordReader), saving the dictionary to a file (using the WordWriter), and performing operations like searching, adding, and removing words.
- Ensure it correctly interacts with the WordReader, WordWriter, and the Word class.

4. **Create the Menu class:**
- Implement the Menu class, which will handle the command-line interface (CLI) and user interactions.
Display the menu options and prompt the user for input.
- Based on the user's input, call the appropriate methods in the DictionaryManager class to perform the desired operation.
- Test the Menu class to ensure it correctly displays the menu and invokes the appropriate methods in the DictionaryManager.

5. **Implement the Utilities class:**
- Create the Utilities class (or any other suitable name) for utility methods.
Implement common utility methods for string validation, parsing, and other operations as needed.
Test the utility methods thoroughly to ensure they work as expected.

6. **Connect the dots:**
- Once all the individual components are working correctly, integrate them into the main application flow.
- Test the entire application end-to-end, ensuring that the user can interact with the CLI, perform various operations on the dictionary, and see the expected results.

7. The main method and the DictionaryApp class which serves as an entry point should be very clean and no logic should be there.

8. **Adhere to software engineering principles:**
- Don't Repeat Yourself (DRY): Identify and eliminate code duplication by extracting common functionality into reusable methods or classes.
- Separation of Concerns (SoC): Ensure that each class or component has a well-defined responsibility and focuses on a specific concern or task.
- Single Responsibility Principle (SRP): Design classes and methods to have a single responsibility, making them more cohesive and easier to maintain.
- Open/Closed Principle (OCP): Design classes and components to be open for extension but closed for modification, allowing for future enhancements without modifying the existing code.

## Part 8. Project submission and Presentation

When you are ready to push your code, push it to your own repository and not the one you cloned the starter code from.

For the dictionary application project, you will be required to present their work in a technical manner, as the application does not have a graphical user interface (GUI). The presentation should focus on explaining the technical aspects of the project, including the design, implementation, and software engineering principles applied.

Since you will have a limited time of 20 minutes for the presentation, it is essential to structure it effectively and cover the most important points listed int the Evaluation below. 

## Part 9. Project Feedback and Evaluation 

### Emphasis should be on the following aspects:

- OOP and Software engineering principles applied.
- Core components and their responsibilities.
- Exception handling, error handling, and input validation.
- File I/O operations (reading and writing dictionary data).
- Command-line interface (CLI) and user interactions.
- Answering all techincal questions in the presentation.

Based on the requirements above, you can earn a maximum of 18 points on this project. Your instructors will score each of your technical requirements using the scale below:

Score | Expectations
----- | ------------
**0** | Incomplete.
**1** | Does not meet expectations.
**2** | Meets expectations. Good job!
**3** | Exceeds expectations. You wonderful creature, you!


## Part 10. Plagiarism

It is essential to maintain academic integrity and ethical standards throughout the development of the dictionary application project. you must adhere to the following strict requirements regarding plagiarism:

1. **No Code Copying:**
- You are strictly prohibited from copying code that they do not fully understand.
- Copying code without comprehending its functionality, logic, and purpose defeats the learning objectives of the project and is considered a dishonesty.

2. **No Use of Other apprentice's Code:**
- You must not use, copy, or incorporate code written by other students.

3. **No Use of AI-Generated Code:**
- Students are strictly prohibited from over using code generated by artificial intelligence (AI) models, language models, or any other automated code generation tools. This means you can use AI to understand concepts and further your understanding on a topic, but you cant copy and past an AI generated code if you dont understand what it does and if you cant explain it during presentation.

You are **encouraged** to seek guidance from instructors, teaching assistants, or peer support resources if they encounter difficulties or have questions during the project development process. Asking for help and clarification is **encouraged** and **does not** constitute plagiarism.