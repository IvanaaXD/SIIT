using CRUDExample.Console;
using CRUDExample.Model.DAO;

namespace CRUDExample;

class Program
{
    static void Main()
    {
        VehiclesDAO vehicles = new VehiclesDAO();
        VehicleConsoleView view = new VehicleConsoleView(vehicles);
        view.RunMenu();
    }
}