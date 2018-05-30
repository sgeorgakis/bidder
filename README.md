# Bidder

This application is implemented using the Spring Boot v2.0.2.RELEASE.
Maven must be installed in order to build and run the application.

## Build

Run `mvn clean install` in order to build the application.
## Run

Run `mvn spring-boot:run` in order to run the application.
Make sure that the port 8080 is not used.
In startup, some sample campaigns are loaded in the mock Campaign API,
found in the link below and making a request to the production server
`https://campaignapi9.docs.apiary.io/#reference/0/campaigns-collection/get-all-campaigns`

The application exposes one endpoint in `/api/bid` path that consumes a POST request
containing a Bid Request in its body, defined in the following link
`https://bidderapi.docs.apiary.io/`

The url for the Campaign API can be specified in the `application.yml` file
using the `application.campaign.url` property.

## Test

Run `mvn test` in order to run the tests.
There are 2 tests available, according to the specifications provided.
In both tests, the sample request and response (if any) is read from a json file
located in the test classpath, as well as the available campaigns that the Campaign API returns.
The response from the application is compared to the expected one.

## Notes

The application uses an interface in order to make the necessary call
to the Campaigns API.
The interface can make a call to any endpoint and cast the result to a specified class.
An implementation of this interface mocks the call and returns the available campaigns
only if the cast type is an array of campaign objects.

Although the Campaign API url can be altered by the user,
it does not affect the result, as the mock service determines the result
by the type to be returned.

Instead of loading the campaigns from a file and keeping them in a variable,
a database could be used (e.g. H2 for simplicity).
I believe that due to the simplicity of the project, such an approach is not necessary.

A future improvement could be to implement a service that makes a real request
and gets real results and be able to choose which service to enable/disable (mock or real request)
by a property in the `application.yml` file or a maven profile.
