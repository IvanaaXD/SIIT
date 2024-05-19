from pymongo import MongoClient

connection_string = "mongodb+srv://radovanovicsv232022:Ivana25i06Veljo@cluster0.plwu4hq.mongodb.net/?retryWrites=true&w=majority"
database_name = "developers"
collection_name = "developers"

client = MongoClient(connection_string)
db = client[database_name]
collection = db[collection_name]

# PRVI ZAD

people = collection.find({
    'status': 'active'
})
for person in people:
    print(person)
print()

# DRUGI ZAD

people = collection.find({
    'name': 'Alice'
})
for person in people:
    print(person)
print()

# TRECI ZAD

people = collection.find({
    'age': {'$gt': 25}
})
for person in people:
    print(person)
print()

# CETVRTI ZAD

people = collection.find({
    'skills': 'Python'
})
for person in people:
    print(person)
print()

# PETI ZAD

people = collection.find({
    'skills': {'$all': ['Python', 'MongoDB']}
})
for person in people:
    print(person)
print()

# SESTI ZAD

people = collection.find({'$or':[
    {'status': 'active'},
    {'age': {'$lt': 30}}
]})
for person in people:
    print(person)
print()

# SEDMI ZAD

people = collection.aggregate([
    {
        '$match': {
            'status': 'active'
        }
    },
    {
        '$group': {
            '_id': 'null',
            'avgerage_salary': {'$avg': '$salary'}
    }}
])
for person in people:
    print(person)
print()

# OSMI ZAD

people = collection.aggregate([
    {
        '$group': {
            '_id': '$status',
            'avgerage_age': {'$avg': '$age'}
    }}
])
for person in people:
    print(person)
print()

# DEVETI ZAD

people = collection.aggregate([
    {
        '$group': {
            '_id': 'null',
            'max_salary': {'$max': '$salary'},
            'min_salary': {'$min': '$salary'}
    }}
])
for person in people:
    print(person)
print()

# DESETI ZAD

people = collection.aggregate([
    {
        '$group': {
            '_id': '$status',
            'number': { "$sum": 1 },
    }}
])
for person in people:
    print(person)
print()

# JEDANAESTI ZAD

people = collection.aggregate([
    {
        '$unwind': '$skills',
    },
    {
        '$group':{
            '_id': '$skills',
            'number': { "$sum": 1 },
        }
    },
])
for person in people:
    print(person)
print()



people = collection.aggregate([
  {
    '$match': {
      '$or': [
        {'age': {'$gt': 25}},
        {'status': "active"}
      ]
    }
  },
  {
    '$project': {
      'name': 1,
      'age': 1,
      'status': 1,
      'skillCount': {'$size': "$skills"},
      'salary': 1
    }
  },
  {
    '$sort': {'salary': -1}
  },
  {
    "$group": {
      "_id": "$status",
      'averageAge': {'$avg': "$age"},
      'averageSalary': {'$avg': "$salary"},
      'maxSalary': {'$max': "$salary"},
      'minSalary': {'$min': "$salary"},
      'total': {'$sum': 1}
    }
  },
  {
    '$sort': {'averageSalary': -1}
  }
])
for person in people:
    print(person)
print()