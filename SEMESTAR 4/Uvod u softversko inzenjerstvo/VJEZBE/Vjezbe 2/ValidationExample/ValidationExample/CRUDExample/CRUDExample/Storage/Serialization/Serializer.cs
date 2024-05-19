using System.Text;

namespace CRUDExample.Serialization;

/*
 * Generička klasa za serijalizaciju objekta u CSV format.
 * Ova klasa zna kako da serijalizuje bilo koji objekat
 * koji implementira ISerializable interfejs.
 */
class Serializer<T> where T : ISerializable, new()
{
    private const char Delimiter = '|';

    public string ToCSV(List<T> objects)
    {
        StringBuilder sb = new StringBuilder();

        foreach (ISerializable obj in objects)
        {
            string line = string.Join(Delimiter.ToString(), obj.ToCSV());
            sb.AppendLine(line);
        }

        return sb.ToString();
    }

    public List<T> FromCSV(IEnumerable<string> lines)
    {
        List<T> objects = new List<T>();

        foreach (string line in lines)
        {
            string[] csvValues = line.Split(Delimiter);
            T obj = new T();
            obj.FromCSV(csvValues);
            objects.Add(obj);
        }

        return objects;
    }
}