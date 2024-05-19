from rdflib import Graph
from pyfuseki import FusekiUpdate


fuseki = FusekiUpdate('http://147.91.177.197:80', 'SV23')
g = Graph()
g.parse('./data/rdf/person_metadata.nt')
fuseki.insert_graph(g)
