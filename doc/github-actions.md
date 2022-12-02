# GitHub-Actions

In order to build an executeable jar and run the tests of our application we use github actions.
We use two build pipelines one for the main and one for the development branch.

# Start Actions

The two seperate Actions get started by either push or Pull requests on the selected branch:

For the main branch:

```
on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]
```

For the development branch:
```
on:
  push:
    branches: [ "development" ]
  pull_request:
    branches: [ "development" ]
```

# The Build

In both pipelines we use an Ubuntu build container to run the build job.
As in the development we use maven as our dependency manager and jdk 17.

```
steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B package --file pom.xml
```

# The Testing

The test are started with the build process of maven by the following lines:

```
    - name: Build with Maven
      run: mvn -B package --file pom.xml
```

# The Download

To be able to download the build artifact the pipelines are finished with the following settings:

```
    - name: Copy built jar
      run: mkdir download & cp target/*.jar download
    
    - name: Upload a Build Artifact
      uses: actions/upload-artifact@v2
      with:
        name: Main-Build
        path: download
```
