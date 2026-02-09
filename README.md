# AutoClean – Java CLI Utility

## Problem
My Downloads folder often becomes cluttered with files of different types, making it difficult to locate important files quickly.

## Solution
AutoClean is a Java-based command-line utility that automatically organizes files into categorized folders such as Images, Documents, Videos, Archives, and Executables.

This tool was built to emphasize simplicity, correctness, and safe file handling.

## How to Run
Compile:
javac AutoClean.java

Run:
java AutoClean

Optional:
java AutoClean <folder_path>

## Design Decisions
- Used Java standard libraries only
- Used Java NIO for safe file operations
- Categorized files based on extensions
- Handled duplicate file names to avoid overwriting

## Assumptions
- File type is determined by extension
- Folders are created only when required

## Sample Output
Screenshots demonstrating the program execution and results are included in the `screenshots/` folder.
ssdsdsdsfcfdsd  dsf