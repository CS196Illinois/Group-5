import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore

cred = credentials.Certificate('cs196-project-af7eb-1dce14b359a5.json')
firebase_admin.initialize_app(cred)
file = open('genres.txt', encoding="utf8")
data = file.read()
genres = data.split("\n")
#print(genres[0])

db = firestore.client()
for i in genres:
    """doc_ref = db.collection(u'genres').document(i)
    doc_ref.set({
        u'genre': i,
        u'effects': [u'effect 1', u'effect 2', u'effect 3']
    })"""
    concentrate = {
        "ambient": 6,
        "chill": 6,
        "classical": 8,
        "jazz": 10
    }
    relax = {
        "classical": 8,
        "ambient": 6,
        "piano": 10,
        "country": 8,
        "jazz": 8,
        "soft rock": 4,
        "soca": 4,
        "reggae": 4,
        "r&b": 4
    }
    motivate = {
        "rock": 6,
        "pop": 8,
        "hip": 10,
        "rap": 10
    }
    please = {
        "electro": 4,
        "edm": 6,
        "pop": 6
    }
    depress = {
        "country": -8,
        "r&b": -6
    }
    distract = {
        "hip": -10,
        "pop": -8
    }
    unsettle = {
        "hip": -10,
        "pop": -8,
        "edm": -6,
        "electronic": -6
    }
    demotivate = {
        "country": -10
    }
    positive = ["concentrate", "relax", "motivate", "please"]
    positiveValues = [0, 0, 0, 0]
    negative = ["depress", "distract", "unsettle", "demotivate"]
    negativeValues = [0, 0, 0, 0]
    list0 = concentrate.keys()
    list1 = relax.keys()
    list2 = motivate.keys()
    list3 = please.keys()
    list4 = depress.keys()
    list5 = distract.keys()
    list6 = unsettle.keys()
    list7 = demotivate.keys()
    for j in list0:
        tmp = i.lower()
        if j in tmp:
            positiveValues[0] = concentrate.get(j)
    for j in list1:
        tmp = i.lower()
        if j in tmp:
            positiveValues[1] = relax.get(j)
    for j in list2:
        tmp = i.lower()
        if j in tmp:
            positiveValues[2] = motivate.get(j)
    for j in list3:
        tmp = i.lower()
        if j in tmp:
            positiveValues[3] = please.get(j)

    for j in list4:
        tmp = i.lower()
        if j in tmp:
            negativeValues[0] = depress.get(j)
    for j in list5:
        tmp = i.lower()
        if j in tmp:
            negativeValues[1] = distract.get(j)
    for j in list6:
        tmp = i.lower()
        if j in tmp:
            negativeValues[2] = unsettle.get(j)
    for j in list7:
        tmp = i.lower()
        if j in tmp:
            negativeValues[3] = demotivate.get(j)
    genreDict = {
        "name": i,
        "positive": positive,
        "positive values": positiveValues,
        "negative": negative,
        "negative values": negativeValues
    }
    print(genreDict)