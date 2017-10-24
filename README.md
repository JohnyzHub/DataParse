                        XML
-----------------------------------------------------------------------
                

          Package				Description
        -----------------------------		----------------------------------------
        Org.w3.dom				Generic DOM API for java

        Org.w3.sax				Interfaces used for the SAX parser

        javax.xml.parsers 			Common interface to DOM and SAX

        javax.xml.bind			        Runtime binding framework including marshalling,
                                                	unmarshalling and validation capabilities.
        javax.xml.transform			XSLT API to transform xml to other forms.

        javax.jason				Provides an API to describe JSON data structure
                                                 and provides the entry point to build, parse,
                                                 read and write json objects and arrays by streaming

        javax.json.spi				Service provider interface(SPI) to plug JsonParser
                                                  and JsonGenerator implementations.

        Javax.json.stream			Provides streaming API to parse and generate Json



    	Marshalling – Transforming the java object into and xml document.
	    UnMarshalling – Transforming XML document into object and optionally validates the Xml.

            System Property				     Description
          ---------------------------------		---------------------------------------
          javax.xml.parsers.DocumentBuilderFactory	Sets DOM builder
          javax.xml.parsers.SAXParserFactory		Configures SAX Parser
          javax.xml.transform.TransformerFactory	Defines which XSLT implementation to use.


The key advantage of XML data format is the ability to transform the xml document from one vocabulary to another.

    DOM: Document Object Model parser reads the XML document to the memory and gives the ability to 
               to traverse the document node by node in any order to parse.

    SAX: Simple API for XML parser is event based parser to parse the xml document. 
          It reads document element by element but not the whole document at once unlike DOM parser. 
          This is a read only 	parser based on push API.

    XPath: XPath is useful to retreive and interpret information represented in XML documents, 
            using expressions.
            Visit - https://examples.javacodegeeks.com/core-java/xml/xpath/java-xpath-examples/

    XSLT: eXtensible Stylesheet Language Transformations provides framework to transform the structure
    		of XML document by combining it with XSL stylesheet to produce an output document.

    StAX: Steaming API for XML provides implementation for processing XML file 
                                  i.e. both read and write based on push API. 
              StAX consists of two sets of APIs. 
                    Iterator based API: Allows the applcation to process xml as a stream of events.
              and 	Cursor based API: Allows the application to process xml one point at a time 
                                        in forward direction only. 

Check: https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.6/tutorial/doc/SJSXP3.html
	http://tutorials.jenkov.com/java-xml/sax-vs-stax.html  
	https://www.journaldev.com/1240/java-xml-parser
				

                                            JSON
                     --------------------------------------------------------------------------
    JSON is a text based language independent format that uses a set of conventions to represent 
                  simple data structures. Json can represent 
                      four primitive data types string, number, boolean and null
                      and two structured types object and array.

    JSON-P: Provides a standard to build a java object in JSON using an API similar to DOM for XML.
		          At the sametime, provides the mechanism to produce and consume JSON by streaming\
		          in a manner similar to StAX for XML.


            Package				                      Description
          -----------------------------		----------------------------------------

          javax.jason				Provides an API to describe JSON data structure 
                                                    and provides the entry point to build, parse, 
                                                    read and write json objects and arrays by streaming

          javax.json.spi			Service provider interface(SPI) to plug JsonParser 
                                                   and JsonGenerator implementations.

          Javax.json.stream			Provides streaming API to parse and generate Json

        JSON-P has methods to create 
          JsonObjectBuilder  –  Builds Json objects.
          JsonArrayBuilder – Builds array of JsonObjects.
          JsonParser – Pull based parsing Json 
              via streaming with forward and read-only access, 
              (Ref BJEE7 Pg:411)
          JsonWriter – Writes Json data to an a resource.
          JsonReader – Reads Json data from a resource.


  For JSON-B check the official documentation: http://json-b.net/users-guide.html   
