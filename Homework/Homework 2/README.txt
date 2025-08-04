Removals:
- I removed the command classes as user input is no longer gathered by the console.

Usage:
- In a shell / command line, change your working directory to the project root directory.
- Modify the config.properties file located in the project root directory and run the jar file with the
associated arguments.

java -jar hw2.jar [kwic-processing | keyword-search <keyword> | index-generation] [absolute path to .properties file]

Notes:
- Regarding CSV files, I was unsure of what the homework instructions meant by "a .csv file in which each sentence is separated by a period, “.”".
When I emailed the professor about formatting, I received no response.
So, I interpreted my own way and each valid CSV (input) for this program is expected to have each sentence on a new line with each word delimited by a period.