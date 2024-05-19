using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using CRUDExample.Model;
using System.Text.RegularExpressions;

namespace GUI.DTO
{
    public class VehicleDTO: INotifyPropertyChanged, IDataErrorInfo
    {
        public int Id { get; set; }

        private string name;
        public string Name
        {
            get
            {
                return name;
            }
            set
            {
                if (value != name)
                {
                    name = value;
                    OnPropertyChanged("Name");
                }
            }
        }
        private int numberOfWheels;
        public int NumberOfWheels {
            get
            {
                return numberOfWheels;
            }
            set
            {
                if (value != numberOfWheels)
                {
                    numberOfWheels = value;
                    OnPropertyChanged("NumberOfWheels");
                }
            }
        }

        public string Error => null;

        private Regex _NameRegex = new Regex("[A-Za-z0-9-]+ [A-Za-z0-9-]+");
        public string this[string columnName]
        {
            get
            {
                if (columnName == "Name")
                {
                    if (string.IsNullOrEmpty(Name))
                        return "Name is required";

                    Match match = _NameRegex.Match(Name);
                    if (!match.Success)
                        return "Format not good. Try again.";

                } 
                else if (columnName == "NumberOfWheels")
                {
                    if (NumberOfWheels <= 0)
                        return "Number of wheels must be a positive value";
                }
                return null;
            }
        }

        private readonly string[] _validatedProperties = { "NumberOfWheels", "Name" };

        public bool IsValid
        {
            get
            {
                foreach (var property in _validatedProperties)
                {
                    if (this[property] != null)
                        return false;
                }

                return true;
            }
        }

        public Vehicle ToVehicle()
        {
            return new Vehicle(name, numberOfWheels);
        }

        public VehicleDTO()
        {
        }

        public event PropertyChangedEventHandler PropertyChanged;

        public VehicleDTO(Vehicle vehicle)
        {
            name = vehicle.Name;
            numberOfWheels = vehicle.NumberOfWheels;
            Id = vehicle.Id;
        }

        protected virtual void OnPropertyChanged(string name)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
        }
    }
}
