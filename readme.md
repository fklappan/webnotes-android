**Abandoned** no further development is planned!

# Webnotes-android

The Android App for the webnotes-backend.

## Configuration

Some gradle build variables have to be provided to communicate and authenticate against the backend:

### serviceUrl

**serviceUrl** points to the root directory (URL) of the webnotes-backend.

For example: 

webnotes-backend installed within https://webnotes.backend

> serviceUrl="https://webnotes.backend"

### serviceUser

**serviceUser** provides the user name for authentication.

Example:

> serviceUser="super user"

### servicePassword

**servicePassword** provides the password for the serviceUser.

Example:

> servicePassword="myPa$$word"

**Please note** that in the current development state, the variables can not be set through the app 
and there will be no proper error messages if one of the variables is not provided.

I recommend to put them into the user scoped gradle.properties file to not accidentially check them into the VCS. 
Usually the file is saved in HOME/.gradle/gradle.properties. If you have a environment variable GRADLE_HOME defined, the file will be saved there.
