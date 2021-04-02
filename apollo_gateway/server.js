const { ApolloServer } = require('apollo-server');
const { ApolloGateway } = require('@apollo/gateway');

const entityHost = process.env.ENTITY_HOST != null ? process.env.ENTITY_HOST : "localhost:8090";
const searchHost = process.env.SEARCH_HOST != null ? process.env.SEARCH_HOST : "localhost:8091";
const imageHost = process.env.IMAGE_HOST != null ? process.env.IMAGE_HOST : "localhost:8092";
const deckHost = process.env.DECK_HOST != null ? process.env.DECK_HOST : "localhost:8093";

// Initialize an ApolloGateway instance and pass it an array of
// your implementing service names and URLs
const gateway = new ApolloGateway({
  serviceList: [
    { name: 'entity', url: `http://${entityHost}/graphql/` },
    { name: 'search', url: `http://${searchHost}/graphql/` },
    { name: 'image', url: `http://${imageHost}/graphql/` },
    { name: 'deck', url: `http://${deckHost}/graphql/` },
    // Define additional services here
  ],
  __exposeQueryPlanExperimental: false,
});

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