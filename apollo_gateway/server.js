const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway');

// Initialize an ApolloGateway instance and pass it an array of
// your implementing service names and URLs
const gateway = new ApolloGateway({
  serviceList: [
    { name: 'entity', url: 'http://localhost:8090/graphql/' },
    { name: 'search', url: 'http://localhost:8091/graphql/' },
    { name: 'image', url: 'http://localhost:8092/graphql/' },
    { name: 'deck', url: 'http://localhost:8093/graphql/' },
    // Define additional services here
  ],
  __exposeQueryPlanExperimental: false,
});

const myPlugin = {

  // Fires whenever a GraphQL request is received from a client.
  requestDidStart(requestContext) {
    console.log('Request started! Query:\n' +
      requestContext.request.query);

    return {

      // Fires whenever Apollo Server will parse a GraphQL
      // request to create its associated document AST.
      parsingDidStart(requestContext) {
        console.log('Parsing started!');
      },

      // Fires whenever Apollo Server will validate a
      // request's document AST against your GraphQL schema.
      validationDidStart(requestContext) {
        console.log('Validation started!');
      },

    }
  },
};

// Pass the ApolloGateway to the ApolloServer constructor
const server = new ApolloServer({
  gateway,
  engine: false,
  plugins: [
    
  ],
  tracing: true,

  // Disable subscriptions (not currently supported with ApolloGateway)
  subscriptions: false,
});

server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});