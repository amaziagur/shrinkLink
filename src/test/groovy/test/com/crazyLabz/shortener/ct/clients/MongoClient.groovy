package test.com.crazyLabz.shortener.ct.clients

import com.mongodb.MongoClientURI

class MongoClient {

    static final String MONGO_URI = "mongodb://amaziagur:xcg6Meje@ds049496.mlab.com:49496/stocker"

    static mongoClient;

    MongoClient() throws UnknownHostException {
        mongoClient = new com.mongodb.MongoClient(new MongoClientURI(MONGO_URI))
    }

    public void drop() {
        mongoClient.dropDatabase("shortener")
    }
}
