using CRUDExample.Controller;
using CRUDExample.Model;
using CRUDExample.Model.DAO;
using CRUDExample.Observer;
using GUI.DTO;
using GUI.View;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace GUI
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window, IObserver
    {

        public ObservableCollection<VehicleDTO> Vehicles { get; set; }
        public VehicleDTO SelectedVehicle { get; set; }
        private VehiclesController vehiclesController { get; set; }

        public MainWindow()
        {
            InitializeComponent();
            DataContext = this;
            Vehicles = new ObservableCollection<VehicleDTO>();
            vehiclesController = new VehiclesController();
            vehiclesController.Subscribe(this);
            Update();
        }



        private void Add_Click(object sender, RoutedEventArgs e)
        {
            AddVehicle addVehicle = new AddVehicle(vehiclesController);
            addVehicle.Show();
        }

        public void Update()
        {
            Vehicles.Clear();
            foreach (Vehicle vehicle in vehiclesController.GetAllVehicles()) Vehicles.Add(new VehicleDTO(vehicle));
        }

        private void Delete_Click(object sender, RoutedEventArgs e)
        {
            if (SelectedVehicle == null)
            {
                MessageBox.Show("Please choose a vehicle to delete!");
            }
            else
            {
                vehiclesController.Delete(SelectedVehicle.Id);
            }
        }
    }
}
