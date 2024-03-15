import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

df = pd.read_csv("pokemon_data.csv")  # dataframe
print(df)
print(df.head())
print(df.columns)
print(df.shape)

print(df.Name)

print(df["Type 1"])

print(df["Type 1"].value_counts())
water = df["Type 1"].value_counts()["Water"]
print(water)

print(df.iloc[0:3])

pd.to_csv("pokemon_data.csv")
