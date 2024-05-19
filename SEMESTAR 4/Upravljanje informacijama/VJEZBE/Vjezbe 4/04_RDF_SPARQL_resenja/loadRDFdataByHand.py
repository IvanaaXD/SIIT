from rdflib import Graph, URIRef, Literal
from pyfuseki import FusekiUpdate

g = Graph()

g.add((URIRef('http://byHand/subject1'), URIRef('http://byHand/hasName'), Literal('Adam')))
fuseki = FusekiUpdate('http://147.91.177.197:80', 'byHand')
fuseki.insert_graph(g)
