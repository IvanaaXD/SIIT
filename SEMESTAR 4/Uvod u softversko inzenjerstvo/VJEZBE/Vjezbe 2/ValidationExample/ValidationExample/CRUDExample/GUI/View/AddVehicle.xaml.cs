using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;
using System.ComponentModel;
using GUI.DTO;
using System.Runtime.CompilerServices;
using System.Diagnostics;
using CRUDExample.Model.DAO;
using CRUDExample.Controller;

namespace GUI.View
{
    /// <summary>
    /// Interaction logic for AddNewVehicle.xaml
    /// </summary>
    public partial class AddVehicle : Window
    {
        public VehicleDTO Vehicle { get; set; }

        private VehiclesController vehiclesController;

        public AddVehicle(VehiclesController vehiclesController)
        {
            InitializeComponent();
            DataContext = this;
            Vehicle = new VehicleDTO();
            this.vehiclesController = vehiclesController;

        }

        private void Add_Click(object sender, RoutedEventArgs e)
        {
            if (Vehicle.IsValid)
            {
                vehiclesController.Add(Vehicle.ToVehicle());
                Close();
            }
            else
            {
                MessageBox.Show("Vehicle can not be created. Not all fields are valid.");
            }
        }
    }
}
