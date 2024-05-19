using CRUDExample.Serialization;

namespace CRUDExample.Model { 

public class Vehicle : ISerializable
{
    public int Id { get; set; }
    public string Name { get; set; }
    public int NumberOfWheels { get; set; }

    public Vehicle()
    {
    }

    public Vehicle(int id, int numberOfWheels, string name)
    {
        Id = id;
        Name = name;
        NumberOfWheels = numberOfWheels;
    }

    public Vehicle(string name, int numberOfWheels)
    {
        Name = name;
        NumberOfWheels = numberOfWheels;
    }

    public override string ToString()
    {
        return $"ID: {Id,5} | Name: {Name,20} | NumberOfWheels: {NumberOfWheels,5} |";
    }

    public string[] ToCSV()
    {
        string[] csvValues =
        {
            Id.ToString(),
            NumberOfWheels.ToString(),
            Name
        };
        return csvValues;
    }

    public void FromCSV(string[] values)
    {
        Id = int.Parse(values[0]);
        NumberOfWheels = int.Parse(values[1]);
        Name = values[2];
    }
}
}