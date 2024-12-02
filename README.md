CMPE 202 Individual Project 
# Project Name

## Overview
This project processes an input text file and generates three JSON files: `apm.json`, `application.json`, and `request.json`.

## Prerequisites
- Java 8 or higher
- Maven 3.x or higher

## Setup



To generate the JSON files from a given input file, run the following command:

    mvn exec:java -Dexec.args="--file src/main/resources/input.txt"


Generated Files:  
apm.json: Contains APM-related data.  
application.json: Contains application-related data.  
request.json: Contains request-related data.







# Describe what problem  you’re solving.

The command-line application efficiently processes log files containing various types of log entries, including APM logs, Application logs, and Request logs. It successfully classifies these logs into specific categories, performs necessary aggregations, and outputs the results in JSON format. The application is designed to be flexible and scalable, allowing for future extensions to support additional log types and file formats.

# What design pattern(s) will be used to solve this?

- **Chain of Responsibility**:
The Chain of Responsibility design pattern has been implemented to address this problem. This pattern is realized through a series of handler objects— `APMLogHandler`, `ApplicationLogHandler`, and `RequestLogHandler` —each responsible for processing a specific type of log entry. The handlers are linked together in a chain, allowing each to process or pass log entries to the next handler in line.

- **Strategy Pattern**:
The Strategy Pattern is well-suited for this application because each log type (APM, Application, Request) requires distinct aggregation logic. By encapsulating the aggregation behavior for each log type into separate, interchangeable strategy classes, the code becomes modular and adheres to the Open-Closed Principle. This pattern allows the `LogProcessor` class to delegate log processing to the appropriate strategy dynamically, depending on the log type. For example, an `APMAggregationStrategy` can calculate statistics like minimum, maximum, median, and average, while a `RequestAggregationStrategy` can handle response time percentiles and status code counts. This approach decouples the classification of logs from their processing logic, making the system extensible and maintainable as new log types or aggregation methods are introduced in the future.


For this project I have implemented Chain of Responsibility pattern.



### Key Features of the Chain of Responsibility Pattern

- **Decoupling Achieved**:  
  The Chain of Responsibility pattern effectively decouples the sender (log entry) from the receiver (log handler), enabling independent processing by multiple handlers without mutual awareness. This enhances modularity and simplifies maintenance.

- **Flexibility Realized**:  
  The design allows for easy addition, removal, or reordering of handlers within the chain, supporting future extensions for new log types or processing logic without disrupting existing functionality.

- **Sequential Processing Ensured**:  
  Log entries are processed in a sequential manner through the chain, ensuring that requests are handled in the appropriate order.

- **Performance Considerations Managed**:  
  While introducing some performance overhead due to sequential processing through multiple handlers, this design ensures that each log type is handled efficiently.

- **Complex Debugging Addressed**:  
  Although debugging can be complex due to the potential for requests to pass through unhandled, clear documentation and logging within handlers aid in tracing request processing paths.


Overall, the Chain of Responsibility pattern provides a robust framework for handling diverse log entries in a structured manner while supporting scalability and maintainability.


### Key Features of the Strategy Pattern

- **Encapsulation of Algorithm**: The Strategy Pattern allows the encapsulation of specific algorithms (in this case, different log aggregation methods) within separate strategy classes. Each log type (APM, Application, Request) will have its own strategy for data processing, ensuring that each log's unique requirements are met.

- **Interchangeability**: Strategy classes are interchangeable, meaning that the LogProcessor can dynamically switch between different strategies depending on the log type. This flexibility is crucial for maintaining a clean and adaptable design, especially when introducing new log types.

- **Separation of Concerns**: By using the Strategy Pattern, the aggregation logic is separated from the rest of the application logic. This improves maintainability by isolating different parts of the system, making it easier to add new functionalities or change existing behaviors without affecting other parts of the system.

- **Open-Closed Principle**: The Strategy Pattern adheres to the Open-Closed Principle, meaning that the system is open for extension but closed for modification. New strategies can be added to handle new log types or aggregation methods without modifying the existing codebase.

- **Simplifies Testing**: Each strategy class can be tested independently, ensuring that the aggregation logic is correct for each log type. Unit tests can be written for each strategy without the need for complex setup or dependencies.



# Consequences of Using the Chain of Responsibility Pattern

### Increased Complexity in Setup
The Chain of Responsibility pattern can lead to an increase in complexity when setting up the chain, as each handler needs to be properly configured and connected. If the chain becomes long or intricate, managing the flow can become more difficult.

### Harder to Debug
When using this pattern, it may be challenging to trace exactly where and why a particular request was handled in a specific way, especially if the chain includes many handlers. This can make debugging and logging more difficult compared to simpler, more direct handling approaches.

### Inefficient Processing in Some Cases
If not carefully designed, the pattern may lead to unnecessary processing, as the request has to pass through each handler in the chain before reaching the appropriate one. This could potentially result in performance overhead if the chain is long and only a few handlers are relevant for a specific log type.

### Risk of Unhandled Requests
If no handler in the chain is appropriate for a particular request, it might go unprocessed. This could lead to issues where certain log types or events are ignored or missed, unless explicitly handled at the end of the chain or through a default handler.

# Consequences of Using the Strategy Pattern

### Increased Number of Classes
Since each log type will require a separate strategy class, the number of classes in the system may increase. While this improves modularity, it could also lead to more files and classes to manage.

### Complexity in Strategy Selection
Although the strategy classes themselves are simple, determining which strategy to use (based on the log type) may introduce complexity in the code, especially if the selection logic isn't well organized.

### Overhead in Small Applications
For smaller applications, the overhead of creating and managing multiple strategy classes might not be justified. However, in this case, it’s a trade-off for ensuring extensibility and flexibility as the log types grow.
