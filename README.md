# DXTRUS API Backend
This repo is for taking data from the server and putting it in the database for the API Frontend to serve.


## Repository Layout
```
.
├── target                                   # Compiled files
├── Common                                   # Common code shared in all modules
│   ├── src                                  # All source files
│   │   ├── main/java/us/dxtrus/api          # Main source
│   │   │   ├── database                     # Interfaces & general code for database code
│   │   │   ├── server                       # Code relating to server data
│   │   │   ├── user                         # Code relating to user data
│   │   ├── test/java                        # Tests
│   └── build.gradle                         # Build file
└── README.md
```