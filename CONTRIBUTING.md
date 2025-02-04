# Contributing

## How to Contribute

This is an open-source project, and we welcome contributions from everyone. This guide will help you understand how to contribute.

### Code of Conduct

By participating in this project, you agree to abide by our [Code of Conduct](./CODE_OF_CONDUCT.md). Please read it to understand what actions will and will not be tolerated.

### Reporting Bugs

If you find a bug, please open an issue on [GitHub](https://github.com/yourusername/colorplayground/issues) with:

- A clear and descriptive title
- A detailed description of the issue
- Steps to reproduce the issue
- Any relevant logs, screenshots, or error messages

### Your First Pull Request

Working on your first Pull Request? You can learn how from this video by [Midudev](https://github.com/midudev)
[How to Contribute to an Open Project on GitHub](https://www.youtube.com/watch?v=niPExbK8lSw&t=358s)

### Submitting a Pull Request

To submit a pull request:

1. Fork the repository.
2. Create a new branch from `main` (e.g., `fet/awesome-feature`).
3. Make your changes in your branch.
4. Write clear and descriptive commit messages.
5. Push your branch to your forked repository.
6. Open a pull request against the `main` branch of the ColorPlayground repository.
7. Ensure your pull request description clearly describes the problem and solution, and includes the issue number if applicable.

### Development Workflow

1. **Clone the repository**:
    ```sh
    git clone https://github.com/0spol/colorplayground.git
    ```

2. **Navigate to the project directory**:
    ```sh
    cd ColorPlayground
    ```

3**Run in Android Studio**:
    - Open the project in Android Studio.
    - Build and run the project on an emulator or connected device.

4. **Testing**:
    - Ensure all tests pass and write new tests for any new features or bug fixes.
    - Check the CI actions after merging, it's important to ensure that everything is running smoothly in the continuous integration pipeline.

### Code Style

Please follow these guidelines to keep the codebase clean and readable:

- Use meaningful and descriptive names for variables and functions.
- Keep functions and methods small and focused on a single task.
- Use comments to explain the purpose of complex code.
- Follow the existing coding style and conventions used in the project.

### Testing

Please write tests for any new features or bug fixes, and ensure all existing tests pass before submitting your pull request.

### Branch Naming Convention

When working on a new feature or task, please follow this branch naming convention before merging to the `main` branch:

- **Feature Branches**:  
  Use the prefix `fet/` followed by a descriptive name of the feature being developed.  
  Examples:
    - `fet/login` (For implementing the login functionality)
    - `fet/profile` (For adding user profile features)

- **Configuration Branches**:  
  Use the prefix `conf/` for branches that involve configuration changes.  
  Examples:
    - `conf/firebase` (For setting up Firebase configuration)

- **Design Branches**:  
  Use the prefix `design/` for design-related changes or improvements.  
  Examples:
    - `design/lookbetter` (For enhancing the visual design of the app)

#### How to Make a Commit

1. **Commit Header:**  
   Choose one of the following options based on the type of change made:

    - `ADD`: For adding new features or files.
    - `FIX`: For fixing errors or bugs.
    - `REFACTOR`: For modifying code without changing its functionality.
    - `DELETE`: For removing code, files, or other entities.
    - `DOCS`: For changes related to documentation.

2. **Commit Content:**  
   Inside the parentheses `()`, specify what was modified in the commit.

3. **Commit Summary:**  
   After `:`, provide a brief and clear summary of the change made.

#### Additional Notes

- Keep commit messages clear and concise.
- Use present tense in the summary (`Implemented`, `Fixed`, `Refactored`, `Deleted`, `Documented`) to indicate the action taken.
- Ensure each commit represents a logical and coherent change in the code or documentation.

### License

By contributing to ColorPlayground, you agree that your contributions will be licensed under the MIT License.

## Thank You!

Your contributions make ColorPlayground better. Thank you for taking the time to contribute!