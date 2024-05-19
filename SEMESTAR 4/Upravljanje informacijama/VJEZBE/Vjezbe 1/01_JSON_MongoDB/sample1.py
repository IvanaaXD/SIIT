from pymongo import MongoClient

connection_string = "mongodb+srv://radovanovicsv232022:Ivana25i06Veljo@cluster0.plwu4hq.mongodb.net/?retryWrites=true&w=majority"
database_name = "sample1"
collection_name = "sample1"

client = MongoClient(connection_string)
db = client[database_name]
collection = db[collection_name]

# PRVI ZAD

men = collection.find({"gender":"male"})
for man in men:
    print(man)
print()

# DRUGI ZAD

men = collection.find({"gender":"male",
                       "from": "Subotica"})
for man in men:
    print(man)
print()

# TRECI ZAD

women = collection.find({"gender":"female",
                       "age": {'$gt': 30}})
for woman in women:
    print(woman)
print()

# CETVRTI ZAD

women = collection.find({"gender":"female",
                       "age": {'$gt': 30, '$lt': 50}})
for woman in women:
    print(woman)
print()

# PETI ZAD

people = collection.find({'number': {'$regex': '^1.*.1'}})
for person in people:
    print(person)
print()

# SESTI ZAD

people = collection.find({'from': {'$in': ['Subotica', 'Novi Sad']}})
for person in people:
    print(person)
print()

# SEDMI ZAD

people = collection.aggregate([
    {
        '$match': {
            'from': 'Subotica'
        }
    },
    {
        '$count': 'broj_osoba' 
    }
])
for person in people:
    print(person)
print()

# OSMI ZAD

people = collection.aggregate([
    {
        '$group': {
            '_id': '$gender',
            'prosjecan_br_godina': {'$avg': '$age'}
        }
    }
])
for person in people:
    print(person)
print()

# DEVETI ZAD

towns = collection.aggregate([
    {
        '$group': {
            '_id': '$from',
            'max_br_godina': {'$max': '$age'}
        }
    }
])
for town in towns:
    print(town)
print()

# DESETI ZAD

towns = collection.aggregate([
    {
        '$group': {
            '_id': '$from',
            'max_br_godina': {'$max': '$age'},
            'ime': {'$first': '$firstName'},
            'prezime': {'$first': '$lastName'},
        }
    }
])
for town in towns:
    print(town)
print()
